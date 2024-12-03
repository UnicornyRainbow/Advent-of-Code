(ns aoc2024.dec2.puz2
  (:require
   [clojure.string :as str]))

(defn read-file []
  (mapv #(str/split % #" ") (str/split-lines (slurp "src/aoc2024/dec2/puz.in"))))

(defn safe-inc-dec [arr op second?]
  (>= 1
      (:skip (reduce
              (fn [x y]
                (if (number? x)
                  (if (and
                       (op x y)
                       (> 4 (if (= op <) (- y x) (- x y)) 0))
                    {:old y :skip 0 :skipped nil}
                    (if second? {:old y :skip 1 :skipped y} {:old x :skip 1 :skipped y}))
                  (if (and
                       (op (:old x) y)
                       (> 4 (if (= op <) (- y (:old x)) (- (:old x) y)) 0))
                    {:old y :skip (:skip x) :skipped (:skipped x)}
                    {:old (:old x) :skip (inc (:skip x)) :skipped (:skipped x)})))
              arr))))

(defn decreasing? [arr]
  (or (safe-inc-dec arr > false)
      (safe-inc-dec arr > true)))

(defn increasing? [arr]
  (or (safe-inc-dec arr < false)
      (safe-inc-dec arr < true)))

(defn parse []
  (let [file (read-file)]
    (apply +
           (for [line file]
             (cond
               (decreasing? (map read-string line)) 1
               (increasing? (map read-string line)) 1
               :else 0)))))

(comment
  (parse))
