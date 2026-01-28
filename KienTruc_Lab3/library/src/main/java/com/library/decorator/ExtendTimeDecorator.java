package com.library.decorator;

public class ExtendTimeDecorator extends BorrowDecorator {
    private int extraDays;

    public ExtendTimeDecorator(BorrowService borrowService, int extraDays) {
        super(borrowService);
        this.extraDays = extraDays;
    }

    @Override
    public double getCost() {
        return borrowService.getCost() + (extraDays * 0.5);
    }

    @Override
    public String getDescription() {
        return borrowService.getDescription() + " + Extend time for " + extraDays + " days";
    }
}
