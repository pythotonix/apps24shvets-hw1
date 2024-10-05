package ua.edu.ucu.apps.tempseries;

import java.util.InputMismatchException;
public class TemperatureSeriesAnalysis {

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp <= barier) {
                throw new InputMismatchException();
            }
        }
        this.tempSeries = temperatureSeries;
    }

    public TemperatureSeriesAnalysis() {
        this.tempSeries = new double[0];
    }

    public double[] getTempSeries() {
        return this.tempSeries;
    }

    public double average() {
        if (this.tempSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (double temp : this.tempSeries) {
            sum += temp;
        }
        return sum / this.tempSeries.length;
    }

    public double deviation() {
        if (this.tempSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double avg = average();
        double sum = 0;
        for (double temp : this.tempSeries) {
            sum += (temp - avg)*(temp - avg);
        }
        return Math.sqrt(sum / this.tempSeries.length);
    }

    public double min() {
        if (this.tempSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double min = this.tempSeries[0];
        for (double temp : this.tempSeries) {
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public double max() {
        if (this.tempSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double max = this.tempSeries[0];
        for (double temp : this.tempSeries) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (this.tempSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double closest = this.tempSeries[0];
        for (double temp : this.tempSeries) {
            if (Math.abs(temp) <= Math.abs(closest)) {
                closest = temp;
                if (Math.abs(temp) == Math.abs(closest)) {
                    closest = Math.abs(temp);
                }
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.tempSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double closest = this.tempSeries[0];
        for (double temp : this.tempSeries) {
            if (Math.abs(temp - tempValue) <= Math.abs(closest - tempValue)) {
                closest = temp;
                if (Math.abs(temp - tempValue) 
                == Math.abs(closest - tempValue)) {
                    closest = Math.abs(temp);
                }
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] res = new double[0];
        for (double temp : this.tempSeries) {
            if (temp < tempValue) {
                double[] newRes = new double[res.length + 1];
                System.arraycopy(res, 0, newRes, 0, res.length);
                newRes[res.length] = temp;
                res = newRes;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] res = new double[0];
        for (double temp : this.tempSeries) {
            if (temp >= tempValue) {
                double[] newRes = new double[res.length + 1];
                System.arraycopy(res, 0, newRes, 0, res.length);
                newRes[res.length] = temp;
                res = newRes;
            }
        }
        return res;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        double[] res = new double[0];
        for (double temp : this.tempSeries) {
            if (temp >= lowerBound && temp <= upperBound) {
                double[] newRes = new double[res.length + 1];
                System.arraycopy(res, 0, newRes, 0, res.length);
                newRes[res.length] = temp;
                res = newRes;
            }
        }
        return res;
    }

    public void reset() {
        this.tempSeries = new double[0];
    }

    public double[] sortTemps() {
        double[] res = this.tempSeries;
        for (int i = 0; i < res.length; i++) {
            for (int j = i + 1; j < res.length; j++) {
                if (res[i] > res[j]) {
                    double temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        return res;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.tempSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        double avg = average();
        double dev = deviation();
        double min = min();
        double max = max();
        return new TempSummaryStatistics(avg, dev, min, max);
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp <= barier) {
                throw new IllegalArgumentException();
            }
        }
        int newSize = this.tempSeries.length*2;
        if (this.tempSeries.length == 0) {
            newSize = temps.length;
        }
        double[] newTempSeries = new double[newSize];
        System.arraycopy(this.tempSeries, 0, newTempSeries, 
        0, this.tempSeries.length);
        System.arraycopy(temps, 0, newTempSeries, 
        this.tempSeries.length, temps.length);
        this.tempSeries = newTempSeries;
        return this.tempSeries.length;
    }

    private double[] tempSeries;
    private int barier = -273;
}
