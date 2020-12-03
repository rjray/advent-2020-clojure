(ns advent-of-code.day03-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day03 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 7]
    (is (= expected (part-1 (slurp (resource "day03-example.txt")))))))

(deftest part2
  (let [expected 336]
    (is (= expected (part-2 (slurp (resource "day03-example.txt")))))))
