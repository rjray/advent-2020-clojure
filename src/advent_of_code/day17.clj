(ns advent-of-code.day17
  (:require [clojure.string :as str]))

(defn- get-input [func input] (func (str/split-lines input)))

(defn- parse-data [input] (mapv vec input))

(defn- build-space [deg start]
  (let [xlen  (count (first start))
        ylen  (count start)
        x     (+ (* deg 2) xlen)
        y     (+ (* deg 2) ylen)
        z     (inc (* deg 2))
        plane (vec (repeat y (vec (repeat x \.))))
        space (vec (repeat z plane))]
    (reduce (fn [sp [y x]]
              (assoc-in sp [deg (+ deg y) (+ deg x)] (get-in start [y x] \.)))
            space (for [x (range xlen), y (range ylen)] (list y x)))))

(def surrounds (vec (for [x (range -1 2), y (range -1 2), z (range -1 2)
                          :when (not (and (zero? x)
                                          (zero? y)
                                          (zero? z)))]
                      [z y x])))

(defn- count-surrounds [space z y x]
  (let [squares (for [move surrounds] (mapv + [z y x] move))]
    (apply + (for [square squares]
               (if (= (get-in space square) \#) 1 0)))))

(defn- change-cell [space z y x]
  (let [cur (get-in space [z y x])
        sur (count-surrounds space z y x)]
    (case cur
      \. (if (= 3 sur)    \# \.)
      \# (if (<= 2 sur 3) \# \.)
      cur)))

(defn- transform [space coords]
  (reduce (fn [b [z y x]]
            (assoc-in b [z y x] (change-cell space z y x)))
          space coords))

(defn- play-life [rounds space]
  (let [xsize (count (ffirst space))
        ysize (count (first space))
        zsize (count space)
        coords (for [x (range xsize), y (range ysize), z (range zsize)]
                 [z y x])]
    (reduce (fn [b _]
              (transform b coords))
            space (range rounds))))

(defn part-1
  "Day 17 Part 1"
  [input]
  (as-> input $
    (get-input parse-data $)
    (build-space 6 $)
    (play-life 6 $)
    (flatten $)
    (frequencies $)
    ($ \#)))

(defn- build-space-4 [deg start]
  (let [xlen  (count (first start))
        ylen  (count start)
        x     (+ (* deg 2) xlen)
        y     (+ (* deg 2) ylen)
        z     (inc (* deg 2))
        plane (vec (repeat y (vec (repeat x \.))))
        cube  (vec (repeat z plane))
        space (vec (repeat z cube))]
    (reduce (fn [sp [y x]]
              (assoc-in sp [deg deg (+ deg y) (+ deg x)]
                        (get-in start [y x] \.)))
            space (for [x (range xlen), y (range ylen)] (list y x)))))

(def surrounds-4 (vec (for [x (range -1 2), y (range -1 2),
                            z (range -1 2), w (range -1 2)
                            :when (not (and (zero? x)
                                            (zero? y)
                                            (zero? z)
                                            (zero? w)))]
                        [w z y x])))

(defn- count-surrounds-4 [space w z y x]
  (let [squares (for [move surrounds-4] (mapv + [w z y x] move))]
    (apply + (for [square squares]
               (if (= (get-in space square) \#) 1 0)))))

(defn- change-cell-4 [space w z y x]
  (let [cur (get-in space [w z y x])
        sur (count-surrounds-4 space w z y x)]
    (case cur
      \. (if (= 3 sur)    \# \.)
      \# (if (<= 2 sur 3) \# \.)
      cur)))

(defn- transform-4 [space coords]
  (reduce (fn [b [w z y x]]
            (assoc-in b [w z y x] (change-cell-4 space w z y x)))
          space coords))

(defn- play-life-4 [rounds space]
  (let [xsize (count (first (ffirst space)))
        ysize (count (ffirst space))
        zsize (count (first space))
        wsize (count space)
        coords (for [x (range xsize), y (range ysize),
                     z (range zsize), w (range wsize)] [w z y x])]
    (reduce (fn [b _]
              (transform-4 b coords))
            space (range rounds))))

(defn part-2
  "Day 17 Part 2"
  [input]
  (as-> input $
    (get-input parse-data $)
    (build-space-4 6 $)
    (play-life-4 6 $)
    (flatten $)
    (frequencies $)
    ($ \#)))
