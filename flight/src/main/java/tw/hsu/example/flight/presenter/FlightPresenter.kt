package tw.hsu.example.flight.presenter

import tw.hsu.example.flight.presenter.FlightContainer

interface FlightPresenter {

    fun arrivalContainer() : FlightContainer;
    fun departureContainer() : FlightContainer;


}