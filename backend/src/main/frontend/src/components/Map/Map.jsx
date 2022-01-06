import React, { Component } from 'react';
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";

export class Map extends Component {
    render() {
        return (
            <MapContainer MapContainer center={[44.80, 15.97]} zoom={7} scrollWheelZoom={false} >

                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                {
                    this.props.data.map(stat => (
                        <Marker key={stat.msetId} position={[stat.location.latitude, stat.location.longitude]}>
                            <Popup>
                                {stat.location.name} <br /> temperatura: {stat.value}
                            </Popup>
                        </Marker>
                    ))
                }
            </MapContainer >
        )
    }
}

export default Map
