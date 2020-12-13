(ns advent-of-code.day04
  (:require [clojure.string :as str]))

(defn- split-up [line]
  (flatten (map #(str/split % #":") (str/split line #" "))))

(defn- create-passports [lines]
  (loop [[line & lines] lines, passports (), passport {}]
    (cond
      (nil? line)   (cons passport passports)
      (empty? line) (recur lines (cons passport passports) {})
      :else         (recur lines passports
                           (apply assoc (cons passport (split-up line)))))))

(defn- is-valid? [needed pp]
  (or (= needed (set (keys pp)))
      (= (disj needed "cid") (set (keys pp)))))

(defn- count-valid [needed passports]
  (count (filter #(is-valid? needed %) passports)))

(defn part-1
  "Day 04 Part 1"
  [input]
  (->> input
       str/split-lines
       create-passports
       (count-valid #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid" "cid"})))

(defn- valid-height? [height]
  (let [[_ val unit] (re-find #"(\d+)(\w+)" (or height ""))
        val          (or val "")
        unit         (or unit "")]
    (cond
      (= unit "cm") (<= 150 (Integer/parseInt val) 193)
      (= unit "in") (<= 59 (Integer/parseInt val) 76))))

(defn- valid-hair? [hair]
  (let [[_ color] (re-find #"#([0-9a-f]+)" (or hair ""))]
    (= (count (or color "")) 6)))

(defn- valid-eyes? [eyes]
  (#{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} eyes))

(defn- valid-pid? [pid]
  (let [[_ digits] (re-find #"(\d+)" (or pid ""))]
    (= (count (or digits "")) 9)))

(defn- between? [value lo hi]
  (and (re-matches #"\d+" (or value ""))
       (<= lo (Integer/parseInt value) hi)))

(defn- data-valid? [pp]
  (and (between? (pp "byr") 1920 2002)
       (between? (pp "iyr") 2010 2020)
       (between? (pp "eyr") 2020 2030)
       (valid-height? (pp "hgt"))
       (valid-hair? (pp "hcl"))
       (valid-eyes? (pp "ecl"))
       (valid-pid? (pp "pid"))))

(defn- count-really-valid [passports]
  (count (filter data-valid? passports)))

(defn part-2
  "Day 04 Part 2"
  [input]
  (->> input
       str/split-lines
       create-passports
       count-really-valid))
