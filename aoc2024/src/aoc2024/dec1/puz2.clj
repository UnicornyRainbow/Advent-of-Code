(ns aoc2024.dec1.puz2
  (:require
   [aoc2024.dec1.puz1 :as puz1]))

(defn compare-lists []
  (let [lists (puz1/map-lists)]
    (loop [left (into [] (:left lists))
           sum 0]
      (let [left-val (peek left)
            ]
        (if (some? left-val)
          (recur
           (pop left)
           (+ sum
              (* left-val
                 (count (filter #(= % left-val) (:right lists))))))
          sum)))))

(comment
  (compare-lists))
