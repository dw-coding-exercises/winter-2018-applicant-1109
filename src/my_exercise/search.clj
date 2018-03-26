(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]))

(defn header [_]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0, maximum-scale=1.0"}]
   [:title "Upcoming Election"]
   [:link {:rel "stylesheet" :href "default.css"}]])

(defn displayLocation [city, state]
  [:div
    [:h1 "city: " city]
    [:h1 "state: " state]])

(defn makeCall [stateURL, cityURL]
  (def baseURL "https://api.turbovote.org/elections/upcoming?district-divisions=")
  (def completeURL (str baseURL stateURL cityURL))
  (println completeURL)
  ; (curl completeURL) -- beginning of curl call
)

(defn createOCDID [city, state]
  (def baseURL "ocd-division/country:us/")
  (def stateURL (clojure.string/lower-case (str "state:" state)))
  (def cityURL (clojure.string/lower-case (str "/place:" city)))
  (def stateFullURL (str baseURL stateURL ","))
  (def completeURL (str baseURL stateURL cityURL))
  (makeCall stateFullURL completeURL)
)

(defn printing [request]
  (def streetObj (get-in request [:form-params]))
  (def userInfo (vals streetObj))
  (def city (nth userInfo 3))
  (def state (nth userInfo 4))

  (createOCDID city state)
  (displayLocation city state)
)

(defn page [request]
  (html5
   (header request)
   (printing request)))

; END OF TWO HOUR TIMESLOT:
; In this file, I defined a namespace called search that I imported in core.clj.
  ; When search.clj is loaded, two functions run via the page function: 
  ; The header and printing functions.

; The printing function cleans the request from the form, allowing 
  ; the city and the state to be saved as variables.
  ; Both are needed for the creation of the OCD-ID.
;The printing function then calls two other functions: displayLocation displays the
  ; user's city and state so they have a visual indicator that the form submitted
  ; and that the information they put in is being examined.
; Then createOCDID is called.

; CreateOCDID builds the OCD-ID url in two steps: first the user's information is 
  ; transformed to be all lowercase strings. Second, the urls are made by collapsing each 
  ; piece of url into one entire string. Two urls are created, one OCD-ID just for
  ; the state, and another for the state and place. Finally, the makeCall function is executed

; The makeCall function formats one final URL for the Democracy Works elections API.
  ; The final url can be observed in the terminal via the println command.

; I was unable to make a successful curl call, as it resulted in the error: 
;   'Unable to resolve symbol: curl in this context'

; NEXT STEPS
; My next steps would be to research how curl works. Do I have to import it into my
  ; application? How do you call curl in clojure? Why am I getting an error that 
  ; indicates to me that curl is not defined?

; Then, I would likely change the request to have the information be returned 
  ; to me in JSON, since I am most familiar with that format. Lastly, I would 
  ; clean the data and then display the necessary information for the user

; THANK YOU
; Thank you so very much for taking the time to review my application. I thoroughly 
  ; enjoyed working on this code challenge! I understand that there are forces 
  ; trying (and succeeding) to make voting inaccessible, which is why the 
  ; technology Democracy Works is building is crucial to creating a just election system.
  ; I am passionate about creating a fair voting experience for all, and I would
  ; work with spirit and tenacity to help forward the mission of Democracy Works.

; Thank you again and I look forward to hearing from you soon.
