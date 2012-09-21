(ns mysparks.templates
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css include-js html5]]
        [hiccup.form-helpers :only [form-to]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "My Sparks"]
               (include-css "/css/reset.css")
               (include-css "/css/bootstrap.css")
               [:link {:rel "stylesheet/less"
                       :type "text/css"
                       :href "/css/style.less"}]
               (include-js "/js/less.js")]
              [:body
               [:div.container
                content]
               (include-js "http://code.jquery.com/jquery-latest.js")
               (include-js "/js/bootstrap.min.js")
               (include-js "/js/app.js")]))

(defpartial base [& content]
            (layout [:div.row [:div.header.span12 [:h1 "My Sparks"]]]
                    content))

(defpartial spark-common [spark]
            [:div.row
              [:div.content.span12 (:content spark)]]
            [:div.row
             [:div.spark-foot.offset9.span3 
              [:span.created
               [:span.soft-label " Created:"]
               [:span.date (:created spark)]]
              [:div.num-additions (count (:additions spark))]]])

(defpartial condensed-spark-block [spark]
  [:div.spark.span12
   (spark-common spark)])

(defpartial addition-block [addition]
            [:div.addition.row
              [:div.content.span12 (:content addition)
             [:span.soft-label " Created:"]
             [:span.date (:created addition)]]])

(defpartial full-spark-block [spark]
            [:div.spark.span12
             (spark-common spark)
             
             [:div.additions.offset1.span10
              (map addition-block (:additions spark))]])

(defpartial root [sparks]
            (base [:div.row [:div.controls.span12
                   [:a#add-spark {:href "#"
                                  :onclick "showNew();"}
                    "Add Spark"]
                   [:a#read-sparks {:href "/read" :target "_blank"}
                    "Read Sparks"]]]
                  [:div.sparks.row
                   [:div.new-spark.span12 {:style "display: none"}
                    (form-to [:post "/sparks"]
                     [:label "Description"]
                     [:textarea#content.span5 {:name "content"
                                         :placeholder "What's the idea?"}]
                     [:div.control-group
                      [:button.btn {:type "submit"} "Submit"]])]
                   (map condensed-spark-block sparks)]))

(defpartial read-sparks [sparks]
            (base [:div.sparks.row
                   (map full-spark-block sparks)]))

