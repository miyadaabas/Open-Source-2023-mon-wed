package com.kirkwood.opensource2023monwed.convresion;
import com.kirkwood.utilities.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Received Assitance from Darryl Shirley
@WebServlet(name= "ConversionServlet", value = "/conversion2")
    public class ConversionServlet extends HttpServlet {

        Map<String, String> results = new HashMap<>();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("WEB-INF/miyada_kilometer_mile.jsp").forward(req, resp);
        }
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String selectedUnit1 = request.getParameter("unit1");
            String inputDistance = request.getParameter("Distance");
            String selectedUnit2 = request.getParameter("unit2");
            //request.setAttribute("selectedUnit", selectedUnit1);

            results.clear();
            results.put("unit1", selectedUnit1);
            results.put("Distance", inputDistance);
            results.put("unit2", selectedUnit2);
            DistanceConverted(selectedUnit1, inputDistance, selectedUnit2);
            request.setAttribute("results", results);
            request.getRequestDispatcher("WEB-INF/miyada_kilometer_mile.jsp").forward(request, response);
        }

        private double convertToMeters(double value) {
            // Conversion from miles to meters
            return value * 1609.34;
        }

        private double convertToMiles(double value) {
            // Conversion from meters to miles
            return value / 1609.34;
        }

        private double convertToMiles2(double value){
            return value / 1.60934;
        }

        private double convertToKilometers(double value) {
            // Conversion from miles to kilometers
            return value * 1.60934;
        }

        public void DistanceConverted(String selectedUnit1, String inputDistance, String selectedUnit2){
            if(!Helpers.isANumber(inputDistance)){
                results.put("wrongInput", "please input a number");
            } else if (Double.parseDouble(inputDistance) >= 0) {
                if(selectedUnit1.contains(selectedUnit2)){
                    Double newDistance = Double.parseDouble(inputDistance);
                    results.put("finalDistance", "Same type so distance: " + Helpers.round(newDistance) + ".");
                } else if(selectedUnit1.contains("Kilometers") && selectedUnit2.contains("Miles")) {
                    Double newDistance = convertToMiles2(Double.parseDouble(inputDistance));
                    results.put("finalDistance", Helpers.round(newDistance) + " Miles");
                } else if(selectedUnit1.contains("Kilometers") && selectedUnit2.contains("Meters")){
                    Double newDistance = Double.parseDouble(inputDistance) * 1000;
                    results.put("finalDistance", Helpers.round(newDistance) + " Meters");
                } else if(selectedUnit1.contains("Meters") && selectedUnit2.contains("Miles")){
                    Double newDistance = convertToMiles(Double.parseDouble(inputDistance));
                    results.put("finalDistance", Helpers.round(newDistance) + " Miles");
                }else if(selectedUnit1.contains("Meters") && selectedUnit2.contains("Kilometers")){
                    Double newDistance = Double.parseDouble(inputDistance) / 1000;
                    results.put("finalDistance", Helpers.round(newDistance) + " Kilometers");
                }else if(selectedUnit1.contains("Miles") && selectedUnit2.contains("Meters")){
                    Double newDistance = convertToMeters(Double.parseDouble(inputDistance));
                    results.put("finalDistance", Helpers.round(newDistance) + "Meters");
                } else if(selectedUnit1.contains("Miles") && selectedUnit2.contains("Kilometers")){
                    Double newDistance = convertToKilometers(Double.parseDouble(inputDistance));
                    results.put("finalDistance", Helpers.round(newDistance) + " Kilometers");
                }else{
                    results.put("SelectedUnitError", "Please select 2 distance types");
                }
            }
            else{
                results.put("InputError", "Please input a non-negative number");
            }
        }
        //updated
    }
