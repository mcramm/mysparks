(ns mysparks.views.welcome
  (:require [mysparks.views.templates :as templates]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/" []
         (templates/root))

(defpage "/read" []
         (templates/read-sparks))
