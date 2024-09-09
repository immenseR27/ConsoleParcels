public enum ParcelErrorCode {
    WRONG_PARCEL_FORM("Обнаружена ошибка в исходных данных: неверная форма посылки. Внесите изменения в исходный файл. В противном случае посылка с ошибкой будет проигнорирована."),
    WRONG_PARCEL_CONTENT("Обнаружена ошибка в исходных данных: неверное содержимое посылки. Внесите изменения в исходный файл. В противном случае посылка с ошибкой будет проигнорирована.");

    private String errorString;

    ParcelErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
