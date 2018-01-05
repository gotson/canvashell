# Canvashell

## About

A simple console drawing application.

## Build & Run

### Requirements

* Java 8 JDK

### Build

To build the project, just run the following command from the root folder:

```gradlew build```

### Run

To run the application, run the following command from the root folder:

```java -jar build\libs\canvashell-0.0.1-SNAPSHOT.jar```

### Usage

Once the application starts, you will be met with a shell invite:

```enter command: ```

You can input a command with its arguments and press `Enter` to execute it.

The following commands are supported:

| Command         | Description                                                                                           |
|-----------------|-------------------------------------------------------------------------------------------------------|
| `C w h`         | Create a new canvas of width 'w' and height 'h'.                                                      |
| `L x1 y1 x2 y2` | Draw a line from (x1,y1) to (x2,y2) with color 'x'. Only horizontal and vertical lines are supported. |
| `R x1 y1 x2 y2` | Draw a rectangle whose opposite corners are (x1,y1) and (x2,y2), with color 'x'.                      |
| `B x y c`       | Bucket fill the entire area connected to (x,y) with color 'c'.                                        |
| `Q`             | Quit the program.                                                                                     |

## Design and implementation choices

### Design

I decided to use Domain Driven Design with onion architecture for this project.

Drawing applications are usually of 2 types: bitmap or vector.
Bitmap only keeps the resulting effect of operations on the drawn canvas, while vector holds a list of drawn objects,
which can be later on modified.

Given the bucket fill operation in vector is quite complex to achieve, as it doesn't have a shape, I decided to
implement a bitmap drawing application.

The domain is quite simple, I am using `Point` to address coordinates, and `Pixel` holds the representation of
one dot of the `Canvas`, which is the centerpiece of our domain.

Drawing operations are performed on the `Canvas`, but as we don't need to keep a list of shapes drawn, using the
_command pattern_ felt natural to implement the different drawing operations.
Each `Command` is self-contained, and by extending an abstract base all commands are validated (Domain validation)
before execution.

For the shell itself, I decided to use _Spring Shell_, first because my goto default framework is always _Spring Boot_,
and second because implementing a shell is quite complex, and leveraging on what the good guys at Pivotal did seemed
like a good idea.

Spring Shell provides out of the box the following features:

* Command and arguments autocompletion
* Command parsing and arguments validation (type and number of arguments)
* Error handling on missing arguments or unknown commands

Note that validation can be done at 2 levels: request (here, the shell) and domain. I decided to rely only on
domain validation, since the validation is mostly required at the whole Command object level (Points being inside the
canvas for example), and not at individual parameters level, and also because _Spring Shell_ already handles the basic
checks around valid commands and parameters.

### Extensibility

Because of the DDD design choices, adding new commands has to be done at 2 levels:

* implement a new command in the `domain.command` package
* add the necessary `@ShellMethod` inside `interfaces.shell.ShellController`

One could argue that we could have added some command metadata (invoke letter, parameters, description) inside the
`Command` object, and generate automatically all the necessary shell input methods (via reflection for example),
but to me that would break the DDD design choice.
Indeed, the domain should not have concerns about how it is called.
For instance, if later on we added a REST interface, or a GUI, each of those interfaces would have their own input
methods, and would not necessarily care about the 'invoke letter' inside the `Command` for instance.
Or maybe we would like to have a different set of available commands per interface.

### Testing

Given the nature of the application, it made sense to perform integration testing with given commands against expected
drawn Canvas or error messages. I decided to use Cucumber for this, which helps to define reusable steps for each
testing scenario.

I did not add any unit tests for the following reasons:

* the `domain.model` package is composed of Data objects
* the `domain.command` package contains some logic, but it is much easier to test using integration tests for the
Canvas result than to try and look programmatically at the content of the resulting `Canvas`
* Spring Shell already provides all the encapsulation necessary for the input commands validation
* Since there are no dependencies to mock, integration tests can cover the full perimeter easily

## Example run

