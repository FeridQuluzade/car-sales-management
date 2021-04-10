package az.turbo.backend.announcements.domain.model;

import az.turbo.backend.bodytypes.domain.model.BodyType;
import az.turbo.backend.brands.domain.model.BrandModel;
import az.turbo.backend.cities.domain.model.City;
import az.turbo.backend.colors.domain.model.Color;
import az.turbo.backend.currencies.domain.model.Currency;
import az.turbo.backend.customers.domain.model.Customer;
import az.turbo.backend.engineVolumes.domain.model.EngineVolume;
import az.turbo.backend.shared.BaseEntity;
import az.turbo.backend.supplies.domain.model.Supplies;

import java.util.Set;

public class Announcement extends BaseEntity {
    private long id;
    private BrandModel brandModel;
    private BodyType bodyType;
    private Color color;
    private Currency currency;
    private EngineVolume engineVolume;
    private City city;
    private Customer customer;
    private Set<Supplies> supplies;

    private FuelType fuelType;
    private TransmissionType transmissionType;
    private DriveType driveType;

    private int ride;
    private double price;
    private boolean isCredit;
    private boolean isBarter;
    private short graduationYear;
    private short engineHP;
    private String description;


}
