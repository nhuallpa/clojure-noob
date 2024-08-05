(ns clojure-noob.clojure-syntax)

;; Clojure-Syntax: https://www.braveclojure.com/do-things/

;;BOOLEAN OPERATORS 
(when true
  (println "Success!")
  "abra cadabra")

(or false nil :nestor :favio)

(or (= 0 1) (= "yes" "no"))

(and :feelin :gaston)

;;NAMING VALUES
;; Binding to a constant
(def failed-protagoniz-name ["Nestor" "Favio" "Huallpa" "Porcel"])

;; FUNTION error-message
(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE " (if (= severity :mild) "MILDLY INCONVENIENCED!" "DOOOOOMED")))

;;DATA STRUCTURE
;; MAP LITERAL
{:first-name "Nestor"
 :last-name "Huallpa"}

{"string-key" +}

;; Nested
{:name {:first "Nestor" :middle "Favio" :last "Huallpa"}}

;; HASHMAP
(hash-map :a 1 :b 2)

;; Get from a MAP
(get {:a 0 :b 1} :b)
(get {:a 0 :b 1} :c "unicorns?")
(get-in {:a 0 :b {:c "oohhh"}} [:b :c])
({:name "Hello world"} :name) ;Map as a function

;; KEYWORD
(:a {:a 1 :b 3}) ; Keyword as a function = 1

;; VECTOR
[2 3 4]
(get ["Hola" 3 {:a "mapa"}] 1)
(vector "Nestor" "Favio" "Huallpa")

;; Adding elements at the end
(conj [1 2 3] 5)

;; LIST
'(1 2 3 4)
(nth '(:a :b :c) 1)

;; Adding elements at the beginning
(conj '(1 2 3) 5)

;; SET
#{"Nestor" 1986 :huallpa}
(hash-set "Nestor" 1986 :huallpa)
(hash-set 1 2 2 3 4 4)

(contains? #{:a :b} :b) ; true
(:a #{:a :b})        ; :a
(get #{:a :b :c} :c) ; :c

;; FUNCTIONS: Higher funtion or first-class function

; Return a function (+)
(or + - )

;Use the function returned
((or + - ) 1 2 3)
((and (= 1 1) +) 1 2 3)
((first [+ :a 1]) 1 2 3)


;Defining
(defn too-enthusiastic                             ; NAME
  "Documentation for this function"                ; DOC
  [name]                                           ; Parameters listed 
  (str "OH. MY. GOD! " name " YOU ARE THE BEST"))  ; Function body

;Arity overloading
(defn multy-arity
  ([first-arg second-arg third-arg]
    (do-thing first-arg second-arg third-arg))
  ([first-arg second-arg]
    (do-thing first-arg second-arg))
  ([first-arg]
    (do-thing first-arg))
)

; Define a defult argument
(defn x-chop
  "Describe the kind of chop you're inflincting on someone"
  ([name chop-type]
    (str "I " chop-type " chop " name " ! Take that"))
  ([name]
    (x-chop name "karate"))
)

; Rest parameters with &
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Santi" "Hulk") ; ("Get off my lawn, Billy!!!" "Get off my lawn, Santi!!!" "Get off my lawn, Hulk!!!")

(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things:" (clojure.string/join ", " things)))

;Destructuring an array
(defn my-first
  [[first-thing]]
  first-thing)
(my-first ["Hola" "Soy" "Nestor"])

; Body
(defn number-comment
  [x]
  (if (> x 6)
    "Oh my god! What a big number!"
    "That number's OK, I guess"))

;; ANONYMOUS FUNCTIONS
  (fn [param-list] ; Not need a name 
    function body)
  ;; Example 1
  (map (fn [name] (str "Hi, " name))
        ["Maxi" "Gustavo" "Ariel"])

  ;; Associate
  (def my-special-multiplier (fn [x] (* x 3)))
  (my-special-multiplier 8)

  ;; Compact anonymous functions
  #(* % 5)
  (#(* % 5) 8) ; 40

  (map #(str "Hi, " %)   ; Use a reader macros.
    ["Alfonzo" "Carlos"])

;; RETURNING FUNCTIONS
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))
(def inc-3 (inc-maker 3))
(inc-3 7)    


;; LET: create a new scope

(let [x 3] 
  x)

(def dalmatian-list 
  ["Nestor" "Favio" "Karate" "Climate"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)

(let [ [nestor & dalmatians] dalmatian-list]
  [nestor dalmatians])


;; INTO and SET
(into [] (set [:a :a])) ; [:a]

;; Loop
(loop [iteration 0]
  (println (str "Interation " iteration))
  (if (> iteration 3)
    (println "goodbay!")
    (recur (inc iteration))))

;; square each number in a vector
(loop [numbers (seq [1 2 3 4 5])  ; one binding 
       result  []]                ; other binding 
  (if numbers
    (let [number (first numbers)]
      (recur (next numbers) (conj result (* number number))))
    result))

;; REDUCE: process a collection a build a result

(reduce + [1 2 3 4 ])

(defn my-reduce 
  ([f initial collection]
    (loop [result    initial
           remaining collection]
      (if (empty? remaining)
        result
        (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
    (my-reduce f head tail)))



;;;  Exercises 1
(defn plus100 
  [number]
  (+ number 100))

;;;  Exercises 2
(defn dec-maker
  [subtrahend]
  #(- % subtrahend))

(def dec-by-10 (dec-maker 10))

(dec-by-10 99)

;; Exercises 3
(defn mapset
  [f items]
  (set (map f items)))


;; CHAPTER 4: FUNCTION IN DEPTH







;; CAPTER 5

;; Recursion instead of for/while
;; Immutable data structures ensure that your code won’t have side effects
(defn sum
  ([vals] 
    (sum vals 0))
  ([vals accumulating-total]
    (if (empty? vals)
      accumulating-total
      (sum (rest vals) (+ (first vals) accumulating-total)))))

;; Using recur keyword

(defn sum
  ([vals] 
    (sum vals 0))
  ([vals accumulating-total]
    (if (empty? vals)
      accumulating-total
      (recur (rest vals) (+ (first vals) accumulating-total)))))


;; Function composition instead of attribute Mutation

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL")) ; function composition of pure functions
(clean "My boa constrictor is so sassy lol!")

;; COMP composition of functions

((comp inc *) 2 3)

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def c-int (comp :intelligence :attributes))  ;; Useful to look up attributes
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))
(spell-slots character)
;; Using comp
(def spell-slots-comp (comp int inc #(/ % 2) c-int))
(spell-slots-comp character)



(defn two-comp
 "Custom comp with two functions"
 [f g]
 (fn [& args]
    (f (apply g args))))

;; example:
(def inc-mult (two-comp inc *))


;; MEMOIZE: Storing the arguments passed to a function and the return value of the function.

(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-identity "Mr. Fantastico")

(def memo-sleepy-identity (memoize sleepy-identity))


;; Java: Class  Clojure: namespace
(ns clojure-noob.clojure-syntax)

;; List
'(1 2 3)
(quote (1 2 3))
(def numbers (list 1 2 3))
(nth numbers 1) ;; valor del indice 1
(conj numbers 4) ;; crea una nueva lista

;; Vector
(def letters (vector "a" "b" "c"))

;; Los keywords actuan como funciones

;; MAP
{"a" 1 "b" 2}
(def countries
  {:co "Colombia"
   :pe "Peru"
  })

(assoc countries :br "Brazil") ; Associete key and value in the map

;; Note: Call funtion in ifs instead of write block of code.
See 
;;cond, when
(def members
  [{:name "nestor" :nationality :pe}])


(def is-co-partial?
  (comp (partial = :co) :nationality))

(filter is-co-partial? members)

;; Destructuring
(defn Destructuring
 [{:keys [path-param]}]
  )
;; group-by 
(group-by :nationality members)

;; Associative Destructuring
;; Example:
(def client {:name "Super Co."
             :location "Philadelphia"
             :description "The worldwide leader in plastic tableware"})

(let [name (:name client)
      location (:location client)
      description (:description client)]
  (println name location "-" description))

;; Using associative destructuring:
(let [{name :name 
      location :location 
      description :description} client]
  (println name location "-" description))

;; Using associative destructuring with :keys reduce redundant information
(let [{:keys [name location description]} client]
  (println name location "-" description))
;= Super Co. Philadelphia - The worldwide leader in plastic tableware.


; Return a new map with the given value at the specified nesting.
(assoc-in {} [:cookie :monster :vocals] "Fintroll")
; {:cookie {:monster {:vocals "Fintroll"}}}

; lets you look up values in nested maps
(get-in {:cookie {:monster {:vocals "Fintroll"}}} [:cookie :monster])
; {:vocals "Fintroll"}