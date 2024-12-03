(ns aoc2024.dec3.puz1)

(defn read-file []
  (re-seq #"mul\((\d{1,3}),(\d{1,3})\)" (slurp "src/aoc2024/dec3/puz.in")))

(defn parse []
  (reduce +
           (map
            (fn [mul]
              (* (read-string (mul 1))
                 (read-string (mul 2))))
            (read-file))))

(comment
  (parse)
  )
