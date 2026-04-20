import { BadRequestException, Inject, Injectable } from '@nestjs/common';
import { BOOKING_EVENTS_PUBLISHER } from '../kafka/kafka.constants';
import {
  BookingEventsPublisher,
  BookingCreatedPayload,
} from '../kafka/booking-events.publisher.interface';
import { PrismaService } from '../prisma/prisma.service';
import { CreateBookingDto } from './dto/create-booking.dto';

export interface BookingView {
  id: string;
  userId: string;
  roomId: string;
  startTime: Date;
  endTime: Date;
  status: 'PENDING' | 'CONFIRMED' | 'CANCELLED';
  createdAt: Date;
}

@Injectable()
export class BookingsService {
  constructor(
    private readonly prismaService: PrismaService,
    @Inject(BOOKING_EVENTS_PUBLISHER)
    private readonly bookingEventsPublisher: BookingEventsPublisher,
  ) {}

  async create(createBookingDto: CreateBookingDto): Promise<BookingView> {
    const startTime = new Date(createBookingDto.startTime);
    const endTime = new Date(createBookingDto.endTime);

    if (startTime >= endTime) {
      throw new BadRequestException('startTime must be before endTime');
    }

    const createdBooking = await this.prismaService.booking.create({
      data: {
        userId: createBookingDto.userId,
        roomId: createBookingDto.roomId,
        startTime,
        endTime,
      },
    });

    const payload: BookingCreatedPayload = {
      bookingId: createdBooking.id,
      userId: createdBooking.userId,
      roomId: createdBooking.roomId,
      startTime: createdBooking.startTime.toISOString(),
      endTime: createdBooking.endTime.toISOString(),
      status: createdBooking.status,
    };
    await this.bookingEventsPublisher.publishBookingCreated(payload);

    return createdBooking;
  }

  findAll(): Promise<BookingView[]> {
    return this.prismaService.booking.findMany({
      orderBy: {
        createdAt: 'desc',
      },
    });
  }
}
