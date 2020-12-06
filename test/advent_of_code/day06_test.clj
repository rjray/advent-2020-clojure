(ns advent-of-code.day06-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day06 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 11]
    (is (= expected (part-1 (slurp (resource "day06-example.txt")))))))

(deftest part2
  (let [expected 6]
    (is (= expected (part-2 (slurp (resource "day06-example.txt")))))))
