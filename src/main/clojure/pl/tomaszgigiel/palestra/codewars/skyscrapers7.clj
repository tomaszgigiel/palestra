(ns pl.tomaszgigiel.palestra.codewars.skyscrapers7
  (:gen-class))

; https://www.codewars.com/kata/7x7-skyscrapers/train/clojure
; In a grid of 7 by 7 squares you want to place a skyscraper in each square with only some clues:

; The height of the skyscrapers is between 1 and 7
; No two skyscrapers in a row or column may have the same number of floors
; A clue is the number of skyscrapers that you can see in a row or column from the outside
; Higher skyscrapers block the view of lower skyscrapers located behind them
; Can you write a program that can solve this puzzle in time?

; This kata is based on 4 By 4 Skyscrapers and 6 By 6 Skyscrapers by FrankK.
; By now, examples should be superfluous; you should really solve Frank's kata first, and then probably optimise some more.
; A naive solution that solved a 4×4 puzzle within 12 seconds might need time somewhere beyond the Heat Death of the Universe for this size.
; It's quite bad.

; Task
; Create

; (defn solve-puzzle [clues] )
; Clues are passed in as a vector[28] of ints The return value is a vector[7] of vector[7] of ints

; All puzzles have one possible solution.
; All this is the same as with the earlier kata.

; Caveat: The tests for this kata have been tailored to run in ~10 seconds with the JavaScript reference solution.
; You'll need to do better than that! Please note the optimization tag.

; Conceptis Puzzles have heaps of these puzzles, from 5×5 (they don't even bother with 4×4) up to 7×7
; and unsolvable within CodeWars time constraints. Old puzzles from there were used for the tests.
; They also have lots of other logic, numbers and mathematical puzzles, and their puzzle user interface is generally nice, very nice.
; (It is, however, Flash, and their mobile offerings are far fewer. Desktop PC recommended.)

(defn solve-puzzle [clues]
  ; Start your coding here...
)
