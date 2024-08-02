(ns clojure-noob.oop-examples)


(defprotocol Concatenable
 (cat [this other]))

; We can extend classes without requiring a intefase avoid 'expression problem'
; The expression problem refers to the desire to implement an existing set of abstract methods for an existing concrete class without having to change the code that defines either.
(extend-type String 
  Concatenable
  (cat [this other]
    (.concat this other)))

(cat "House" "of leaves")

(extend-type java.util.List
  Concatenable
  (cat [this other]
    (concat this other)))

(cat [1 2 3] [4 5 6])