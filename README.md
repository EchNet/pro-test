# PRO-TEST 

PRO-TEST is an imaginary on-line retailer committed to the business of selling stuff.

## Architecture

Web front end (not included here).

Java engine obtained from some big software company, such as IBM or Oracle (also
not included here) with custom business logic plug-ins developed by the local IT 
staff.

## Entities

*Product:* Something for sale.
	
*Customer:* A customer account.

*Order:* A purchase.  Has a Customer and a list of purchased Items.

*Item:* A purchased Product, its purchase price, and an optional Promotion.

*Promotion:* A map of product IDs to discount prices.

## BUG!!!

PRO-TEST customer service reports that whenever a customer tries to return one
or more items for store credit and not for any items in exchange, they are sent
to the error page!  This is a serious defect that must be fixed immediately!

What went wrong?

---

"The lady doth PRO-TEST too much, methinks."
