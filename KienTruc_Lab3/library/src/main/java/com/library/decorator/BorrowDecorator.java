package com.library.decorator;

public abstract class BorrowDecorator implements BorrowService {
    protected BorrowService borrowService;

    public BorrowDecorator(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @Override
    public double getCost() {
        return borrowService.getCost();
    }

    @Override
    public String getDescription() {
        return borrowService.getDescription();
    }
}
