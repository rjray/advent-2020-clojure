(ns advent-of-code.day21-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day21 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 5]
    (is (= expected (part-1 (slurp (resource "day21-example.txt")))))))

(deftest part2
  (let [expected "mxmxvkd,sqjhc,fvjkl"]
    (is (= expected (part-2 (slurp (resource "day21-example.txt")))))))
