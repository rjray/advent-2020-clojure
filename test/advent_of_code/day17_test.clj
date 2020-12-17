(ns advent-of-code.day17-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day17 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 112]
    (is (= expected (part-1 (slurp (resource "day17-example.txt")))))))

(deftest part2
  (let [expected 848]
    (is (= expected (part-2 (slurp (resource "day17-example.txt")))))))
