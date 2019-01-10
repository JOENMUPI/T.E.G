# T.E.G., or our final CompEng project

## What's all of this?

This is supposed to be the back-end for a Restaurant Managemente system, written in Java. It should provide some basic functionality regarding Stock, Employees, Products and whatnot. The thing is the system's objects are distributed and connect using Corba. It's all about giving a spin to something that would be, otherwise, seen as trivial.

## What's in this repository?

As of now you will find here the source for some Java Classes and a few UML Diagrams. Also an example IDL file for Objects.

## How does this work? What's up with the nomenclature?

### Workflow

So, the general ideal behind this is:

- Business Objects talk to Connection, giving information about the connection (IP, Port, Session).
- Connection talks to ProxySecurity, regarding if the action is allowed or not<sup>1</sup>, then ProxySecurity forwards to Process.
- Process takes Parameters and Data from the requests, checks if they are OK and where they should go to.
- Process tells DB to manage data accordingly.
- Data regarding the result of the operation is returned to the client from DB using the details from Connection.

For more details, check the diagrams. There are plans for a LoadBalancer and a Log of some sort.

<sup>1</sup>: Login/Signup should sort of bypass ProxySecurity, since there's no need to check permissions for those actions.

### Nomenclature/Structure

Having talked about workflow, it should be easier to understand what's inside the IDL:

- **byt** is a **byt**e array that can be returned by functions.
- **baseS** is the **base s**tructure for a request.
- **XC** is the data transfered between any Object (AKA **X**) and **C**onnection.
- **PB** is the data transfered between **P**rocess and any Business Object.
- **XD** is the data transfered between any Object (AKA **X**) and **D**B.
- **baseI** is the **base i**nterface for Objects.
- PtoB, XtoD, and XtoC have similar purposes, to carry data between two Objects. E. g., **P**to**B** means "**P**rocess gets data from **B**usiness Object" (the "to" is confusing, we know).

Nomenclature could be better, but this will do.

## Why are you doing this?

Why not?

## Who is working on this?

José Mundo ([JOENMUPI](https://github.com/JOENMUPI), creator of the repo) and Andrés Hernández ([auroszx](https://github.com/auroszx)), from URU (Universidad Rafael Urdaneta). Venezuela.

