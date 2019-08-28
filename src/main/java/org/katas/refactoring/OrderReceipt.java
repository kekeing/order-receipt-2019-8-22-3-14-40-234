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
    private void printLineItems(StringBuilder output,double totalSalesTax,double totalAmount){
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription());
            output.append('\t');
            output.append(lineItem.getPrice());
            output.append('\t');
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append(lineItem.totalAmount());
            output.append('\n');
            double salesTax = calculateSalesTax(lineItem);
            totalSalesTax += salesTax;
            totalAmount = calculateTotalAmount(lineItem);
        }


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
        double totalSalesTax = 0d;
        double totalAmount = 0d;
       printLineItems(output,totalSalesTax,totalAmount);

        output.append("Sales Tax").append('\t').append(totalSalesTax);
        output.append("Total Amount").append('\t').append(totalAmount);
        return output.toString();
    }
}