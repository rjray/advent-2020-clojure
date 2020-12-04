(ns advent-of-code.day04bis
  (:require [clojure.string :as str]))

(defn- create-passports [chunks]
  (map #(apply hash-map (flatten (map rest (re-seq #"(\w{3}):([^\s]+)" %))))
       chunks))

(defn- setup-data [input]
  (-> input (str/split #"\n\n") create-passports))

(defn- is-valid? [passport]
  (let [ks (set (keys passport))]
    (= (count (disj ks "cid")) 7)))

(defn part-1
  "Day 04 Part 1"
  [input]
  (->> input
       setup-data
       (filter is-valid?)
       count))

(defn- between? [value lo hi]
  (and (re-matches #"\d+" (or value ""))
       (<= lo (Integer/parseInt value) hi)))

(defn- valid-height? [height]
  (let [[_ val unit] (re-find #"(\d+)(\w+)" (or height ""))]
    (cond
      (= unit "cm") (between? val 150 193)
      (= unit "in") (between? val 59 76)
      :else         false)))

(defn- valid-hair? [hair]
  (re-matches #"^#[0-9a-f]{6}$" (or hair "")))

(defn- valid-eyes? [eyes]
  (#{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} eyes))

(defn- valid-pid? [pid]
  (re-matches #"^\d{9}$" (or pid "")))

(defn- is-really-valid? [pp]
  (and (between? (pp "byr") 1920 2002)
       (between? (pp "iyr") 2010 2020)
       (between? (pp "eyr") 2020 2030)
       (valid-height? (pp "hgt"))
       (valid-hair? (pp "hcl"))
       (valid-eyes? (pp "ecl"))
       (valid-pid? (pp "pid"))))

(defn part-2
  "Day 04 Part 2"
  [input]
  (->> input
       setup-data
       (filter is-really-valid?)
       count))
