import { BadRequestException } from '@nestjs/common';
import { Test, TestingModule } from '@nestjs/testing';
import { BOOKING_EVENTS_PUBLISHER } from '../kafka/kafka.constants';
import { BookingEventsPublisher } from '../kafka/booking-events.publisher.interface';
import { PrismaService } from '../prisma/prisma.service';
import { BookingsService } from './bookings.service';
import { CreateBookingDto } from './dto/create-booking.dto';

describe('BookingsService', () => {
  let bookingsService: BookingsService;
  let publishBookingCreatedSpy: jest.SpiedFunction<
    BookingEventsPublisher['publishBookingCreated']
  >;

  const prismaServiceMock = {
    booking: {
      create: jest.fn(),
      findMany: jest.fn(),
    },
  };

  const bookingEventsPublisherMock: BookingEventsPublisher = {
    publishBookingCreated: jest.fn(),
  };

  beforeEach(async () => {
    jest.clearAllMocks();
    publishBookingCreatedSpy = jest.spyOn(
      bookingEventsPublisherMock,
      'publishBookingCreated',
    );

    const module: TestingModule = await Test.createTestingModule({
      providers: [
        BookingsService,
        {
          provide: PrismaService,
          useValue: prismaServiceMock,
        },
        {
          provide: BOOKING_EVENTS_PUBLISHER,
          useValue: bookingEventsPublisherMock,
        },
      ],
    }).compile();

    bookingsService = module.get<BookingsService>(BookingsService);
  });

  it('creates a booking and publishes BOOKING_CREATED event', async () => {
    const createBookingDto: CreateBookingDto = {
      userId: '1096f4fc-b303-4769-aa9d-ac74caf31f87',
      roomId: 'fa9857f2-7082-4946-8bc8-9ec3ddf9f53f',
      startTime: '2026-05-01T10:00:00.000Z',
      endTime: '2026-05-01T11:00:00.000Z',
    };

    const createdBooking = {
      id: '5a78e3f8-c26f-4bcb-8ac3-ca3cf7fb322d',
      userId: createBookingDto.userId,
      roomId: createBookingDto.roomId,
      startTime: new Date(createBookingDto.startTime),
      endTime: new Date(createBookingDto.endTime),
      status: 'PENDING' as const,
      createdAt: new Date('2026-01-01T00:00:00.000Z'),
    };

    prismaServiceMock.booking.create.mockResolvedValue(createdBooking);
    publishBookingCreatedSpy.mockResolvedValue(undefined);

    const result = await bookingsService.create(createBookingDto);

    expect(prismaServiceMock.booking.create).toHaveBeenCalledWith({
      data: {
        userId: createBookingDto.userId,
        roomId: createBookingDto.roomId,
        startTime: new Date(createBookingDto.startTime),
        endTime: new Date(createBookingDto.endTime),
      },
    });
    expect(publishBookingCreatedSpy).toHaveBeenCalledWith({
      bookingId: createdBooking.id,
      userId: createdBooking.userId,
      roomId: createdBooking.roomId,
      startTime: createdBooking.startTime.toISOString(),
      endTime: createdBooking.endTime.toISOString(),
      status: createdBooking.status,
    });
    expect(result).toEqual(createdBooking);
  });

  it('throws 400 when startTime is not before endTime', async () => {
    const createBookingDto: CreateBookingDto = {
      userId: '1096f4fc-b303-4769-aa9d-ac74caf31f87',
      roomId: 'fa9857f2-7082-4946-8bc8-9ec3ddf9f53f',
      startTime: '2026-05-01T12:00:00.000Z',
      endTime: '2026-05-01T11:00:00.000Z',
    };

    await expect(bookingsService.create(createBookingDto)).rejects.toBeInstanceOf(
      BadRequestException,
    );

    expect(prismaServiceMock.booking.create).not.toHaveBeenCalled();
    expect(publishBookingCreatedSpy).not.toHaveBeenCalled();
  });
});
