package me.lukasz.cli.extracli;

import me.lukasz.TeslaMain;
import me.lukasz.cli.MainCLI;
import me.lukasz.database.entities.Car;
import me.lukasz.database.manager.CarManager;
import me.lukasz.utils.MsgUtil;
import me.lukasz.utils.Scan;

import java.util.ArrayList;

public class CarCLI extends MainCLI
{

    public void viewAllCars()
    {
        CarManager carManager = new CarManager();
        ArrayList<Object> arrayList = carManager.getArrayObjects();
        for (Object object : arrayList)
        {
            Car car = (Car) object;
            printText(car.toString());
        }
        this.sendExit();
    }

    public void addCar()
    {
        Car car = new Car();
        boolean askagain = true;
        printText(" * Please Enter Model Name:");
        car.setModel_name(Scan.getString());
        printText(" * Please Enter Model Version: (Standard, Long_Range, Performance)");
        do
        {
            try
            {
                car.setModel_version(Car.modelVersion.valueOf(Scan.getString().toUpperCase()));
                askagain = false;
            } catch (Exception e)
            {
                printText(" * Wrong Value! Please Try Again!");
                askagain = true;
            }
        } while (askagain);
        printText(" * Please Enter Price");
        car.setPrice((float) Scan.getDouble());
        printText(" * Please Enter Year");
        car.setYear(Scan.getInt());
        printText(" * Please Enter Colour");
        car.setColour(Scan.getString());
        printText(" * Please Enter Zero To Sixty");
        car.setZeroToSixty((float) Scan.getDouble());
        printText(" * Please Enter Top Speed");
        car.setTopSpeed((float) Scan.getDouble());
        printText(" * Please Enter Range WLTP");
        car.setRange_wltp((float) Scan.getDouble());
        printText("Here are the Car Details, Reply Y to Create or N to Discard!");
        switch (Scan.getString().toLowerCase())
        {
            case "y":
                new CarManager().createRecord(car);
                printTextPrefix("Car was Successfully Created!");
                sendExit();
                break;
            case "n":
                printTextPrefix("Car creation was unsuccessful!");
                sendExit();
                break;
            default:
                printTextPrefix("Invalid input!");
                sendExit();
                break;
        }
    }

    public void updateCar()
    {
        CarManager carManager = new CarManager();
        Car car = null;
        boolean moreChange = true;
        printText("Please enter the UID of the Car you wish to Edit!");
        final String UID = Scan.getString();
        if (carManager.getRecordObject(UID) == null)
        {
            printText("Car UID not found! Sending back to Main Menu");
            this.optionsPerm(TeslaMain.employee);
        } else
        {
            car = (Car) carManager.getRecordObject(UID);
            do
            {
                printText("Use the Following Commands to Update Customer!");
                printText(" * ModelName <ModelName>");
                printText(" * ModelVersion <ModelVersion>");
                printText(" * Price <Price>");
                printText(" * Year <Year>");
                printText(" * Colour <Colour>");
                printText(" * ZeroToSixty <ZeroToSixty>");
                printText(" * TopSpeed <TopSpeed>");
                printText(" * RangeWLTP <RangeWLTP>");
                printText(" * Exit - Exit Customer Update Menu");
                String[] args = Scan.getString().split(" ");
                switch (args[0].toLowerCase())
                {
                    case "modelname":
                        carManager.setString(car.getId(), "model_name", MsgUtil.joinString(args, args[0]));
                        break;
                    case "modelversion":
                        Car.modelVersion temp = Car.modelVersion.STANDARD;
                        try
                        {
                            temp = Car.modelVersion.valueOf(MsgUtil.joinString(args, args[0]));
                        } catch (Exception e)
                        {
                            printText(" * Wrong Value! Please Try Again!");
                        }
                        carManager.setString(car.getId(), "model_version", temp.toString());
                        break;
                    case "price":
                        if (MsgUtil.isNumber(args[1]))
                        {
                            carManager.setDouble(car.getId(), "price", Double.parseDouble(args[1]));
                        } else
                        {
                            printText("Wrong Input, a Number is Required!");
                        }
                        break;
                    case "year":
                        if (MsgUtil.isNumber(args[1]))
                        {
                            carManager.setDouble(car.getId(), "year", Double.parseDouble(args[1]));
                        } else
                        {
                            printText("Wrong Input, a Number is Required!");
                        }
                        break;
                    case "colour":
                        carManager.setString(car.getId(), "colour", MsgUtil.joinString(args, args[0]));
                        break;
                    case "zerotosixty":
                        if (MsgUtil.isNumber(args[1]))
                        {
                            carManager.setDouble(car.getId(), "zero_to_sixty", Double.parseDouble(args[1]));
                        } else
                        {
                            printText("Wrong Input, a Number is Required!");
                        }
                        break;
                    case "topspeed":
                        if (MsgUtil.isNumber(args[1]))
                        {
                            carManager.setDouble(car.getId(), "top_speed", Double.parseDouble(args[1]));
                        } else
                        {
                            printText("Wrong Input, a Number is Required!");
                        }
                        break;
                    case "range_wltp":
                        if (MsgUtil.isNumber(args[1]))
                        {
                            carManager.setDouble(car.getId(), "range_wltp", Double.parseDouble(args[1]));
                        } else
                        {
                            printText("Wrong Input, a Number is Required!");
                        }
                        break;
                    case "exit":
                        moreChange = false;
                        break;
                }
            }
            while (moreChange);
            this.sendExit();
        }
    }

    public void deleteCar()
    {
        CarManager carManager = new CarManager();
        Car car = null;
        printText("Please enter the UID of the customer you wish to delete!");
        final String UID = Scan.getString();
        if (carManager.getRecordObject(UID) == null)
        {
            printText("Car CRID not found! Sending back to Main Menu");
            this.optionsPerm(TeslaMain.employee);
        } else
        {
            car = (Car) carManager.getRecordObject(UID);
            printText("Car Found! Please review the Information and Confirm!");
            printText("");
            printText(car.toString());
            printText("");
            printText("Type Y to delete Customer | Type N to discard and try again");
            switch (Scan.getString().toLowerCase())
            {
                case "y":
                    carManager.deleteRecord(car.getId());
                    printText("Car Deleted | Sending back to Main Menu");
                    this.sendExit();
                    break;
                case "n":
                    this.deleteCar();
                    break;
                default:
                    this.sendExit();
                    break;
            }
        }
    }
}
