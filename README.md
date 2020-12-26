# advent-2020-clojure

This is my code for the 2020 [Advent of Code](https://adventofcode.com/2020), all solutions in [Clojure](https://clojure.org/).

All code is under the `src` directory. Each solution-file is named `dayNN.clj` and contains both puzzle solutions for that day. These are the publically-facing functions `part-1` and `part-2`. These files are the code *exactly as I used it to solve and submit the answers*. If I revisit any of the days and try to clean up or optimize the solutions, that work will be in a separate file that will be named `dayNNbis.clj`. (Except that I will likely go back and comment the code after the fact, when I'm not racing the clock.)

The `resources` directory contains the input data for each day. These files are named for the day (`dayNN.txt`), and files with the example input are named `dayNN-example.txt`.

The `test` directory contains unit tests. I'm experimenting with these for the first time this go-around, so I'll say more here once I have a better feel.

## Stats

Number of answers correct on first submission: 45/50 (90%)

Highest finish for first half: 1662 (day 19)

Highest finish for second half: 793 (day 19)

## Usage

This project is managed with [Leiningen](https://leiningen.org/). Running the following will download any dependencies and start a REPL:

```
lein repl
```

## Postmortem

Advent of Code 2020 is now completed. Overall, I did well. I placed 3rd on both leaderboards that I'm on (NVIDIA and Clojure), and only had one day (day 20) that exceeded 24 hours' time in getting both solutions. I did a little worse in terms of submitting wrong answers; last year I only submitted a wrong answer twice, for an average of 96%. This year I had five wrong submissions for an average of 90%.

It is very likely that I won't be able to participate in AoC next year (2021) or the year after (2022). I'm starting a MS program in Computer Science next month and expect Decembers to be when final exams land. But I will be following the #adventofcode channel on the Clojure slack, as this year it was much more active than I remember last year being. I had some great interaction with other Clojure programmers this time around.

## Links

I'd like to link to three other Clojure repos in particular:

* [Aleksandr Zhuravlёv (zelark)](https://github.com/zelark/AoC-2020)
* [Vincent Cantin (green-coder)](https://github.com/green-coder/advent-of-code-2020)
* [Nazarii Bardiuk (nbardiuk)](https://github.com/nbardiuk/adventofcode)

I learned a great deal from several of the coders I talked to on slack, but these three especially. Zelark's code is particularly concise and clean.

## Lessons and Take-Aways

My take-aways from this year (for when I can return to AoC) are:

1. I need better templates to work with. I told myself this last year but never got around to it. I used Mitchell Hanburg's starter as a basis for my own, but I can see a lot of areas in which I can put my own spin on it to better suit my style:
   1. Either extend the unit-test elements or drop them. They didn't prove useful and the command to run each (`lein test path/to/file.clj`) was just too much typing when in a hurry. It was faster to just have the example input loaded into a `def` in the REPL.
   1. More abstractions for input parsing, based on past experiences. This is also linked to the next (top-level) point, but I did spent too much time each day repeating stuff that could/should have been in the templates I was using.
   1. Make the [advent-clojure-basis](https://github.com/rjray/advent-clojure-basis) repository work as a template, so that I can start off new years with less up-front tweaking.
1. Desperately need some utils-code written, in particular around the reading/parsing of the input data.
   1. The starter that I based mine on covers this partially, in that it places all the input data files under the `resources` directory and reads them as such. Then it provides a `-main` fn that handles calling a given day/part, feeding it the input already read in via `slurp`.
   1. To this I should add common cases, including: breaking into lines, breaking into blocks (using `\n\n` as a split-separator), lines of integers, etc.
   1. One useful pattern I adopted from someone else, was to have a generic `parse-input` that takes both the input and a fn, splits the input into lines and feeds them to the given function. Might be a way to extend this even more with `comp`, maybe?
   1. Get some basic stuff in as well, such as:
      1. Defining/manipulating coordinate systems (2D, hexagonal)
      1. Basic searching (A*, etc.)
      1. ASCII-art-based operations (parsing, rotation, etc.)
1. Lastly, I need to use Clojure more during the year.
   1. I spent far too much time looking up basic functions for their arguments' type and/or order.
   1. I made certain mistakes too often (like `(cons list value)` instead of `(cons value list)`).
   1. There are still a *lot* of Clojure keywords I don't even know because I haven't used them before (not to mention the ones that I don't even know that I don't know).

## Credits

This is based on my [Advent of Code Clojure Basis](https://github.com/rjray/advent-clojure-basis) repository, which itself is based almost entirely on Mitchell Hanburg's [Advent of Code Clojure Starter](https://github.com/mhanberg/advent-of-code-clojure-starter).

## License

Copyright © 2020 Randy J. Ray

Distributed under the Eclipse Public License either version 2.0 or (at your option) any later version.
