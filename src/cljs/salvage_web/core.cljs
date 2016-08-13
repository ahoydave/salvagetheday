(ns salvage-web.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.string :as str]))

(defonce app-state (atom {:hero-text  "Time to SALVAGE THE DAY!"
                          :hide-steps false}))

(defn hello []
  (let [{:keys [hero-text hide-steps] :as st} @app-state]
    [:div
     [:h1 hero-text]
     [:div {:hidden hide-steps}
      [:ol
       [:li
        [:a {:href "#" :on-click (fn [e]
                                   (let [window js/Window]
                                     (-> (js/open "https://www.youtube.com/watch?v=c0E_bhaE3T0")
                                         .-blur)
                                     (js/focus window)))} "Tunes"]
        " to get your head in the game"]
       [:li "What are the top 3 things that you wanted to do today?"
        [:ol
         [:li {:type "a"}
          [:input {:type    :text
                   :style   {:width 400}
                   :on-blur (fn [e]
                              (let [t (.-target.value e)]
                                (if (-> t str/blank? not)
                                  (swap! app-state assoc
                                         :hero-text (str "Just go " t)
                                         :hide-steps (not hide-steps)))))}]]
         [:li {:type "a"}
          [:input {:type  :text
                   :style {:width 400}}]]
         [:li {:type "a"}
          [:input {:type  :text
                   :style {:width 400}}]]]]]
      [:button {:disabled true} "Start Salvaging"]]]))

(defn mount-root []
  (reagent/render [hello] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
