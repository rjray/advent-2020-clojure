(ns advent-of-code.day08bis
  (:require [clojure.string :as str]))

(defn- parse-instructions [instr]
  (vec (reverse (reduce (fn [acc in]
                          (let [[op arg] (str/split in #" ")]
                            (cons (list op (Integer/parseInt arg)) acc)))
                        () instr))))

(defn- exec-until-loop [code]
  (let [end  (count code)]
    (loop [pc 0, acc 0, seen #{}]
      (cond
        (>= pc end) {:end acc}
        :else
        (let [[op arg] (code pc)]
          (cond
            (seen pc)    {:loop acc}
            (= op "nop") (recur (inc pc) acc (conj seen pc))
            (= op "acc") (recur (inc pc) (+ acc arg) (conj seen pc))
            (= op "jmp") (recur (+ pc arg) acc (conj seen pc))))))))

(defn part-1
  "Day 08 Part 1"
  [input]
  (->> input
       str/split-lines
       parse-instructions
       exec-until-loop))

(defn- make-variants [code]
  (let [end   (count code)
        xlate {"acc" "acc", "nop" "jmp", "jmp" "nop"}]
    (loop [idx 0, variants ()]
      (cond
        (= idx end) (reverse variants)
        :else
        (recur (inc idx) (cons (assoc code
                                      idx (list (xlate (first (code idx)))
                                                (last (code idx))))
                               variants))))))

(defn part-2
  "Day 08 Part 2"
  [input]
  (->> input
       str/split-lines
       parse-instructions
       make-variants
       distinct
       (map exec-until-loop)
       (filter :end)))
