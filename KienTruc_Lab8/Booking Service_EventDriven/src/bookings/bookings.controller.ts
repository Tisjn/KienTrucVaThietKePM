import { Body, Controller, Get, Post } from '@nestjs/common';
import { BookingsService, BookingView } from './bookings.service';
import { CreateBookingDto } from './dto/create-booking.dto';

@Controller('bookings')
export class BookingsController {
  constructor(private readonly bookingsService: BookingsService) {}

  @Post()
  create(@Body() createBookingDto: CreateBookingDto): Promise<BookingView> {
    return this.bookingsService.create(createBookingDto);
  }

  @Get()
  findAll(): Promise<BookingView[]> {
    return this.bookingsService.findAll();
  }
}
