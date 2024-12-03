(ns aoc2024.dec1.puz1
  (:require
   [clojure.string :as str]))

(defn read-file []
  (mapv #(str/split % #"   ") (str/split-lines (slurp "src/aoc2024/dec1/puz.in"))))

(defn map-lists []
  (loop [left []
         right []
         file (read-file)]
    (let [last (peek file)]
      (if (some? last)
        (recur
         (conj left (read-string (last 0)))
         (conj right (read-string (last 1)))
         (pop file))
        {:left (sort left) :right (sort right)}))))

(defn compare-lists []
  (let [lists (map-lists)]
    (loop [left (into [] (:left lists))
           right (into [] (:right lists))
           diff 0]
      (let [left-val (peek left)
            right-val (peek right)]
       (if (and (some? left-val) (some? right-val))
        (recur
         (pop left)
         (pop right)
         (+ diff
            (if (pos? (compare left-val right-val))
              (- left-val right-val)
              (- right-val left-val))))
         diff)))))

(comment
  (read-file)
  (map-lists)
  (compare-lists)
  )
