(ns advent-of-code.day18-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day18 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 26457]
    (is (= expected (part-1 (slurp (resource "day18-example.txt")))))))

(deftest part2
  (let [expected 694173]
    (is (= expected (part-2 (slurp (resource "day18-example.txt")))))))
