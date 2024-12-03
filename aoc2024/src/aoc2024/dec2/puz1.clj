(ns aoc2024.dec2.puz1
  (:require
   [clojure.string :as str]))

(defn read-file []
  (mapv #(str/split % #" ") (str/split-lines (slurp "src/aoc2024/dec2/puz.in"))))

(defn inc-dec [arr op]
  (and (apply op arr)
       (not (zero?
             (reduce (fn [x y]
                       (if (> 4
                              (if (= op <) (- y x) (- x y))
                              0)
                         y
                         0))
                     arr)))))

(defn decreasing? [arr]
  (inc-dec arr >))

(defn increasing? [arr]
  (inc-dec arr <))

(defn parse []
  (let [file (read-file)]
    (apply +
           (for [line file]
             (cond
               (decreasing? (map read-string line)) 1
               (increasing? (map read-string line)) 1
               :else 0)))))

(comment
  (parse)
  )
