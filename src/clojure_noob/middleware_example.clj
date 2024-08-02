; https://clojure-doc.org/articles/cookbooks/middleware/#:~:text=Middleware%20in%20Clojure%20is%20a,clj%2Dhttp%2C%20and%20Kit.

; Middleware in Clojure is a common design pattern for threading a request through a series of functions designed
; to operate on it as well as threading the response through the same series of functions.

(ns clojure-noob.middleware_example
  (:require [clj-http.client :as http]
            [clojure.string :as str]))

(defn client [request]
  (http/get (:site request) (:option request)))

(client {:site "http://www.google.com" :options {}} )

; Create a middleware function
(defn wrap-no-op
  "Middleware function that doesn't do anything"
  [client-fn]
  (fn [request]
    (client-fn request)))

; Use the middleware
(def new-client (wrap-no-op client))

(new-client {:site "http://www.google.com" :options {}} )

(defn wrap-https
  [client-fn]
  (fn [request]
    (let [site (:site request)
          new-site (str/replace site "http:" "https:")
          new-request (assoc request :site new-site)]
      (client-fn new-request))))

(defn wrap-https-arrow
  [client-fn]
  (fn [request]
    (-> request
        (update :site #(str/replace % "http:" "https:"))
        (client-fn))))

; test the middleware for https
(def https-client (wrap-https-arrow client))
(https-client {:site "http://www.google.com" :options {}})

; Combining middleware
; create a new to use as example
(defn wrap-add-date
  [client]
  (fn [request]
    (let [response (client request)]
      (assoc response :date (java.util.Date.)))))

;combine
(def my-client (wrap-add-date (wrap-https (wrap-no-op client))))

; Combine using treading macro
(def my-client
  (-> client
      wrap-no-op
      wrap-https
      wrap-add-date))

(my-client {:site "http://google.com"})
