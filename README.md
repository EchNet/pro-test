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

*ExchangeOrder:* A purchase made in exchange for one or more returned items.

*Item:* A purchased Product, its purchase price, and an optional Promotion.

*Promotion:* A map of product IDs to discount prices.

## The Problem

PRO-TEST is having customer service issues.  Its return/exchange policy allows for 
any purchased item to be returned for a full refund within 30 days of purchase. 
However, some customers who return items that were purchased at a sale price feel
that they should be able to exchange for similar items, even after the sale has
ended.  Instead, they are forced to pay the difference in price.  The following
specification is intended to fix this problem.  Now it's up to the IT team to 
put together an implementation.

## Specification: Return/Exchange Policy 

Any subset of the items in an order may be returned for store credit, exchange 
items, or a combination of the two, within 30 days of the date purchase.  An 
"exchange order" must be created and processed.

In most cases, the customer's account is credited with the total purchase price of
the returned items, and charged with the total current price of the exchange items.
There are two exceptions.

1.  An item may be exchanged for another item of the same product ID regardless of
	any difference between the purchase price and the current price.  For example,
	a customer may purchase a blue Z-Brand sweater at a time when a promotional 
	discount is available, then exchange it for a green Z-Brand sweater after the
	promotion has expired, at no cost.

2.  If a promotional discount was applied to any of the returned items at the time
	of purchase, then the same promotion is considered active at the time of 
	exchange, even if it has expired.  For example, if a customer purchases a 
	sweater while a 30% discount on all sweaters is active, the customer may later
	exchange the sweater for any other at 30% off.

---

"The lady doth PRO-TEST too much, methinks."
