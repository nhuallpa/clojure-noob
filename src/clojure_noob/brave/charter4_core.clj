(ns clojure-noob.brave.charter4-core
  (:require [clojure.test :refer :all]
            ))

(seq '(1 2 3))
(seq [1 2 3])
(seq #{1 2 3})


; Convert a map to a list
(seq {:name "Bill compton" :occupation "Dead mopey guy"})


; seq always returns a value that looks and behaves like a list
(deftest test-seq
  (is (= (seq {:name "Bill compton" :occupation "Dead mopey guy"})
         '([:name "Bill compton"] [:occupation "Dead mopey guy"]))))

; Convert the seq back into a map by using 'into'
(into {} (seq {:a 1 :b 2 :c 3}))


;Transforming by Map
(map inc [1 2 3 4 5]) ; Incremente each value

; Pass multiple collection in map
(def counts
  [1 3 4 5])
(def prices
  [30 10 22 50])
(map * counts prices)



;; Map with multiples collection
(map str ["a" "b" "c"] ["A" "B" "C"])

;; Example
(def human-consumption   [1 2 4 2 3 6])
(def critter-consumption [99 88 66 99 7 7])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)

;; Map with multiples functions
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg])) ; % take function as parameters
(stats [3 4 10])
(stats [10 20 30 40 40 90])          ; Iterate over a vector of functions



;; Map for getting keywords from a map
(def identities
  [{:alias "Batman" :real "Bruce waynes"}
   {:alias "SpiderMan" :real "Peter Parker"}
   {:alias "Santa" :real "Claus"}
   {:alias "Bunny" :real "Buzz"}])

(map :real identities)


;::::::::::::: REDUCE :::::::::::::

; Reduce a Map to produce a Map.
(reduce (fn [new-map [key value]]
          (assoc new-map key (inc value)))
        {}
        {:min 30 :max 60})                      ;Sequence of vector like ([:min 30] [:max 10])
; {:min 31, :max 61}

; Reduce to filter keys base on their values
(reduce (fn [new-map [key value]]
          (if (> value 4)
            (assoc new-map key value)
            new-map))
        {}
        {:human 4.1 :critter 3.9})
; {:human 4.1}

;::::::: Take, Drop, Take-while, and Drop-while :::::::

(take 3 [ 4 5 67 8 9])
; (4 5 67)
(drop 1 [9 8 6 8])
; (8 6 8)

;; Take while
(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

;; Retrieve elements from january and february's data.
;; No process all the elements
(take-while #(< (:month %) 3) food-journal)

;; Drop elements from January and February's data
(drop-while #(< (:month %) 3) food-journal)

;; Combine take-while and drop-while
;; Retrieve February an March's data
(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))


;::::::::::::: Filter and Some ::::::::

(filter #(< (:month %) 3) food-journal)
; take-while could be more efficient with ordered data.

(some #(< (:month %) 3) food-journal)
; true

;; Using some to verify and return the entry adding "and"
(some #(and (< (:month %) 3) %) food-journal)
; {:month 1, :day 1, :human 5.3, :critter 2.3}

;::::::: SORT AND SORT-BY | CONCAT :::::::
(sort [3 4 6 2 3 7 8])
(sort-by count ["aaaa" "bb" "Nestor"])

(concat [2 34 5] [2 4 6 3])


;:::::::::::: LAZY SEQs :::::::::