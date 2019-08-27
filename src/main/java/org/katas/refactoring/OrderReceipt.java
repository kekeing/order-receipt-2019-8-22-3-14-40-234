package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;
    private StringBuilder output;

    public StringBuilder getOutput() {
        return output;
    }

    public void setOutput(StringBuilder output) {
        this.output = output;
    }

    public Order getOrder() {
        return order;
    }

    public OrderReceipt(Order order) {
        this.order = order;
    }
    private void printHeaders(StringBuilder output){
        output.append("======Printing Orders======\n");
    }
    private void printCustomerInfomation(StringBuilder output){
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
    }
    private void printLineItems(StringBuilder output){


    }
    private double calculateSalesTax(LineItem lineItem){
        double salesTax = lineItem.totalAmount() * .10;
        return salesTax;
    }
    private double calculateTotalAmount(LineItem lineItem){
        double totalAmount = 0d;
        double salesTax = calculateSalesTax(lineItem);
        totalAmount += lineItem.totalAmount() + salesTax;
        return totalAmount;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        printHeaders(output);
        printCustomerInfomation(output);

        // prints lineItems
        double totalSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');

            // calculate sales tax @ rate of 10%
            double salesTax = calculateSalesTax(lineItem);
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalAmount = calculateTotalAmount(lineItem);
        }

        // prints the state tax
        output.append("Sales Tax").append('\t').append(totalSalesTax);

        // print total amount
        output.append("Total Amount").append('\t').append(totalAmount);
        return output.toString();
    }
}