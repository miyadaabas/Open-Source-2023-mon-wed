<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Miyada's Meter Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
    <div class="container my-4">
        <h1>Miyada's Distance Converter</h1>
        <form method="POST" action="conversion2">
            <select name="unit1" class="form-select" aria-label="Select a type of Distance">
                <option>
                    Select a type of Distance
                </option>
                <option ${results.selectedUnit1 == 'Meters' ? 'selected' : ''} value="Meters">
                    Meters
                </option>
                <option ${results.selectedUnit1 == 'Kilometers' ? 'selected' : ''} value="Kilometers">
                    Kilometers
                </option>
                <option ${results.selectedUnit1 == 'Miles' ? 'selected' : ''} value="Miles">
                    Miles
                </option>
            </select>
            <div class="form-group mb-2">
                <label for="Distance">Please Input Distance</label>
                <Input name="Distance" value="${results.get("inputDistance")}" type="text" class="form-control" id="Distance">
            </div>
            <select name="unit2" class="form-select" aria-label="Select a type of Distance">
                <option>
                    Select a 2nd type of Distance for conversion
                </option>
                <option ${results.selectedUnit2 == 'Meters' ? 'selected' : ''} value="Meters">
                    Meters
                </option>
                <option ${results.selectedUnit2 == 'Kilometers' ? 'selected' : ''} value="Kilometers">
                    Kilometers
                </option>
                <option ${results.selectedUnit2 == 'Miles' ? 'selected' : ''} value="Miles">
                    Miles
                </option>
            </select>
            <br/>
            <button type="submit" class="btn-primary">
                submit
            </button>
        </form>
            <c:if test="${!results.finalDistance.isEmpty()}">
                <p>
                    ${results.finalDistance}
                </p>
            </c:if>
            <c:if test="${!results.wrongInput.isEmpty()}">
                <p>
                        ${results.wrongInput}
                </p>
            </c:if>
            <c:if test="${!results.SelectedUnitError.isEmpty()}">
                <p>
                        ${results.SelectedUnitError}
                </p>
            </c:if>
            <c:if test="${!results.inputError.isEmpty()}">
                <p>
                        ${results.inputError}
                </p>
            </c:if>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
