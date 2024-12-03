(ns aoc2024.dec3.puz2
  (:require
   [clojure.string :as str]))

(defn read-file []
  (reduce concat
          (map #(re-seq #"mul\((\d{1,3}),(\d{1,3})\)" %)
               (re-seq #"(?:^|do\(\)).*?(?:don't\(\)|$)"
                       (str/replace (slurp "src/aoc2024/dec3/puz.in") "\n" "")))))

(defn parse []
  (reduce +
          (map
           (fn [mul]
             (* (read-string (mul 1))
                (read-string (mul 2))))
           (read-file))))

(comment
  (parse))
