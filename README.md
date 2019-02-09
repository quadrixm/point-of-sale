# point-of-sale scanning API


## Compiling Application

Go to the root directory and

Run

```bash
$ javac src/*.java
```

## Running Application

Run

```bash
$ cd src/
$ java Main
```

When the application starts, it will print the current default pricing.
And will ask to continue with default pricing or add new pricing.

```bash
Current pricing
A:   $2.0 each or 4 for $7.0
B:   $12.0 each
D:   $0.15 each
C:   $1.25 each or 6 for $6.0
Please enter 'continue' to continue with current pricing or 'new' to add new pricing
Please enter 'exit' to exit this program
>>> 
```

### Continuing with exiting pricing

If you like to continue with existing price enter `continue`

```bash
>>> continue
```

It will ask you to enter product codes in serial i.e `ACDDC`

```bash
Current pricing
A:   $2.0 each or 4 for $7.0
B:   $12.0 each
D:   $0.15 each
C:   $1.25 each or 6 for $6.0
Enter product codes in serial
>>> 
```

Now Enter new product codes
```bash
>>> ACDDC
```

It will print total amount and will ask to enter new product codes or exit

```bash
$9.0
Enter new product codes
Please enter 'exit' to exit this program
>>> 
```

You can exit or can keep adding on more product codes.


### Setting up new pricing

If you like to set new pricing enter `new` when the application starts.

```bash
>>> new
```

It will ask you to enter new pricing in a format

```bash
Enter new Pricing in the format <Product Code> <Per Unit Price> <Volume Amount (Optional)> <Volume Price  (Optional)>
>>> 
```

Enter product code space per unit price. For instance if a product code is A and per unit cost is $1.5

Enter

```bash
>>> A 1.5
```

If it has volume price than next append space volume amount space volume pricing

```bash
>>> A 1.5 4 5
```

Note: Volume amount and volume price is optional

After adding this will print

```bash
Current pricing
A:   $1.5 each or 4 for $5.0
Enter new Pricing in the format <Product Code> <Per Unit Price> <Volume Amount (Optional)> <Volume Price  (Optional)>
Please enter 'continue' to continue with current pricing
Please enter 'exit' to exit this program
```

It will show you all existing pricing. 
If you would like to add more pricing, add it in the given format.

And when you are done, enter `continue` with all added pricing.





