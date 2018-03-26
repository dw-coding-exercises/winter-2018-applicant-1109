(ns my-exercise.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [my-exercise.home :as home]
            [my-exercise.search :as search]))

(defroutes app
  (GET "/" [] home/page)
  (POST "/search" [request] search/page)
  (route/resources "/")
  (route/not-found "Not found"))

(def handler
  (-> app
      (wrap-defaults site-defaults)
      wrap-reload))

; My first step in this file was to import the search.clj file I created (line 7).
; Then I made a POST request to the search.clj file that calls the page function.
; That function runs any HTML (or hiccup) on the page, and also any function 
  ; that is called in the page function
; This allows my page to display after the search button is clicked!