(ns mysparks.views.welcome
  (:require [mysparks.views.templates :as templates]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(def example-sparks
  [
   {:content "This is the first spark I created. It is just a test."
    :created "2012-09-11 11:05:32"
    :additions [
                {:content "This is an addition to the spark. Sparks only be added to."
                 :created "2012-09-11 13:03:45"}
                {:content "Yet another addition"
                 :created "2012-09-11 14:17:62"}]}
   {:content "This is the second spark I created. Yes, it is another test."
    :created "2012-09-11 11:05:32"
    :additions [
                {:content "This spark only has one addition to it."
                 :created "2012-09-11 14:17:62"}]}
   ])
(defpage "/" []
         (templates/root example-sparks))

(defpage "/read" []
         (templates/read-sparks example-sparks))
