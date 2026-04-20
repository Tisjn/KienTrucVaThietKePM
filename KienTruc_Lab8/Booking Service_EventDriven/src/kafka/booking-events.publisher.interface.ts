export interface BookingCreatedPayload {
  bookingId: string;
  userId: string;
  roomId: string;
  startTime: string;
  endTime: string;
  status: 'PENDING' | 'CONFIRMED' | 'CANCELLED';
}

export interface BookingCreatedEvent {
  eventId: string;
  eventType: 'BOOKING_CREATED';
  timestamp: string;
  payload: BookingCreatedPayload;
}

export interface BookingEventsPublisher {
  publishBookingCreated(payload: BookingCreatedPayload): Promise<void>;
}
