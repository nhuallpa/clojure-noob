(ns clojure-noob.oop-examples)

(defprotocol Concatenable
 (cat [this other]))

(extend-type String 
  Concatenable
  (cat [this other]
    (.concat this other)))

(cat "House" "of leaves")