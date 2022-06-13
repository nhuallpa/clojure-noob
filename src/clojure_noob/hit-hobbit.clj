;; Okay! It's time to use your newfound knowledge for a noble purpose: smacking around hobbits! 
;;To hit a hobbit, you’ll first model its body parts. 
;Each body part will include its relative size to indicate how likely it is that that part will be hit.
;To avoid repetition, the hobbit model will include only entries for left foot, left ear, and so on. 
;Therefore, you’ll need a function to fully symmetrize the model, creating right foot, right ear, 
;and so forth. Finally, you’ll create a function that iterates over the body parts and randomly 
;chooses the one hit. Along the way, you’ll learn about a few new Clojure tools: let expressions, 
;loops, and regular expressions. Fun!
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expect a secuence of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts 
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
       (recur remaining
              (into final-body-parts
                    (set [part (matching-part part)])))))))

;; With Reduce

(defn better-symmetrize-body-parts
  "Expects a sequens of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
            []
            asym-body-parts))


;; Hobbit Violance
(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts 
            accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit asym-hobbit-body-parts)


;;                      EXAMPLE WITH ALIEN
;; Aliens Body
(def asym-alien-body-parts [{:name "head" :size 3}
                             {:name "side-eye" :size 1}
                             {:name "side-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "side-shoulder" :size 3}
                             {:name "side-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "side-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "side-kidney" :size 1}
                             {:name "side-hand" :size 2}
                             {:name "side-knee" :size 2}
                             {:name "side-thigh" :size 4}
                             {:name "side-lower-leg" :size 3}
                             {:name "side-achilles" :size 1}
                             {:name "side-foot" :size 2}])


(defn matching-part-alien
  [part number-edge]
  (loop [index-edge 2
         result-edge []]
    (if (> index-edge number-edge)
      result-edge
      (recur (inc index-edge) 
             (into result-edge (set [{:name (clojure.string/replace (:name part) #"^side-" (str "side" index-edge "-"))
                                :size (:size part)}])))))
  )

;; With Reduce

(defn better-symmetrize-body-parts-alien
  "Expects a sequens of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (clojure.set/union (set [part]) (matching-part-alien part 5))))
            []
            asym-body-parts))

;; Test
(better-symmetrize-body-parts-alien asym-alien-body-parts)

;; Aliens violence
(defn hit-alien
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts-alien asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts 
            accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit-alien asym-alien-body-parts)
