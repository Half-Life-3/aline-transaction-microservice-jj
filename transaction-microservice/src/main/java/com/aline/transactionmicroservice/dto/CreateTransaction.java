package com.aline.transactionmicroservice.dto;

import com.aline.core.validation.annotation.AccountNumber;
import com.aline.transactionmicroservice.model.TransactionMethod;
import com.aline.transactionmicroservice.model.TransactionStatus;
import com.aline.transactionmicroservice.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * Create a TransactionRequest to make a transaction
 * to a specified account with the specified amount.
 *
 * This DTO is used to manually create a transaction.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransaction {

    /**
     * The type of transaction.
     * Specifies whether it was a purchase, payment,
     * refund, etc...
     */
    @NotNull
    private TransactionType type;

    /**
     * The method the transaction used whether
     * it was through a credit card or ACH.
     */
    @NotNull
    private TransactionMethod method;

    /**
     * The amount of the transaction in cents.
     */
    @PositiveOrZero
    private int amount;

    /**
     * The initial status of the transaction.
     */
    @NotNull
    private TransactionStatus status;

    /**
     * The merchant this transaction was
     * made to (This is only required if the
     * transaction was a purchase, deposit, refund,
     * payment, or void).
     * @see TransactionType
     */
    private String merchantCode;

    /**
     * A simple merchant name will suffice but can be updated
     * to a full qualifying merchant name. The name does not have
     * to be unique. This is only required if the merchant code
     * specified may not already exist!
     */
    private String merchantName;

    /**
     * Description of the transaction
     */
    @Size(max = 255)
    private String description;

    /**
     * Card number is required if account number
     * is not specified.
     */
    @CreditCardNumber
    private String cardNumber;

    /**
     * Account number is required if card number
     * is not specified.
     */
    @AccountNumber
    private String accountNumber;

}
