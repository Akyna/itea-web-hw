<!DOCTYPE html>
<html lang='en'>
<head><title>HW08</title>
    <style>
        .main-container-wrapper {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        div.main-container-wrapper .main-form, div.main-container-wrapper p {
            color: #8A8A8A;
        }

        .main-container {
            height: 100%;
            width: 100%;
            display: flex;
            flex-direction: column;
            align-content: center;
            align-items: center;
            justify-content: flex-start;
        }

        .main-form {
            width: 90%;
        }

        select, .wide-input {
            width: 147px;
        }

        .form-row {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 4px 0 4px 0;
        }

        .input-wrapper div.wide-input {
            padding: 0;
        }

        .input-wrapper div, .input-error {
            padding: 0 10px 0 10px;
        }

        input[type=\"checkbox\" i] {
            margin: 0;
        }

        div h3 {
            text-align: center;
            text-transform: uppercase;
        }

        div.input-error {
            flex: 2;
            color: red;
        }

        .input-wrapper {
            flex: 3;
            display: flex;
            justify-content: flex-end;
            align-items: center;
        }

        textarea {
            width: 141px;
            height: 50px;
            resize: none;
        }

        div.input-wrapper div.wide-input label.single-checkbox {
            display: flex;
        }
    </style>
</head>
<body>