package tw.hsu.example.plane.presenter

interface ContainPresenter {

    fun arrivalContainer() : FlightContainer;
    fun departureContainer() : FlightContainer;

    fun currencyContainer() : CurrencyContainer;



}