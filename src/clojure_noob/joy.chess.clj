(ns clojure-noob.joy.chess)

(def ^:dynamic *file-key* \a)
(def ^:dynamic *rank-key* \0)

(defn initial-board []
  [\r \n \b \q \k \b \n \r
   \p \p \p \p \p \p \p \p
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \- \- \- \- \- \- \- \-
   \P \P \P \P \P \P \P \P
   \R \N \B \Q \K \B \N \R])

(defn- file-component [file]
  (- (int file) (int *file-key*)))

(defn- rank-component [rank]
  (->> (int *rank-key*)
       (- (int rank))
       (- 8)
       (* 8)))

(defn- index [file rank]
  (+ (file-component file) (rank-component rank)))

(defn lookup [board pos]
  (let [[file rank] pos]
    (board (index file rank))))

(lookup (initial-board) "a1")


; LOCAL ENCAPSULATION
(defn lookup3 [board pos]
  (let [[file rank] (map int pos)
        [fc rc] (map int [\a \0])
        f (- file fc)
        r (* 8 (- 8 (- rank rc)))
        index (+ f r)]
    (board index)))

(lookup3 (initial-board) "a1")