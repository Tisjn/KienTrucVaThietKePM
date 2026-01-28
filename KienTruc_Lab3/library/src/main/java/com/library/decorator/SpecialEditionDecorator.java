package com.library.decorator;

public class SpecialEditionDecorator extends BorrowDecorator {
    private String editionType;

    public SpecialEditionDecorator(BorrowService borrowService, String editionType) {
        super(borrowService);
        this.editionType = editionType;
    }

    @Override
    public double getCost() {
        return borrowService.getCost() + 2.0;
    }

    @Override
    public String getDescription() {
        return borrowService.getDescription() + " + Special Edition: " + editionType;
    }
}
