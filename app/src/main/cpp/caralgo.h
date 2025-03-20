#ifndef CARALGO_H
#define CARALGO_H

#include <vector>
using namespace std;


typedef struct PointI {
	int x;
	int y;
	PointI():x(0),y(0) {}
	PointI(int xx,int yy):x(xx),y(yy) {}
	void operator() (int xx,int yy) {x=xx;y=yy;}
	void reset() {x=y=0;}
}PointI,*PointIPtr;

typedef struct SizeI{
	int width;
	int height;
	SizeI():width(0),height(0) {}
	SizeI(int w,int h):width(w),height(h) {}
	void operator() (int w,int h) {width=w;height=h;}
	void reset() {width=height=0;}
} SizeI,*SizeIPtr;

typedef struct ImageBGRA{
	int *data = nullptr;
	SizeI size;
	ImageBGRA():data(nullptr),size() {}
	ImageBGRA(int *buf,const SizeI& sz):data(buf),size(sz) {}
	void reset() {data=nullptr;size.reset();}
} ImageBGRA,*ImageBGRAPtr;

class CarAlgo
{
public:
	CarAlgo();
	~CarAlgo();

	void setInvalid(void);
	void imtAlgoClear(void);

	bool setCalibration(float calibration);
	float calibration(void) const;

	double fps(void) const;
	void setFps (double fps);

	bool setImtThreshold(int thr);
	int imtThreshold(void);

	bool setROI(int roiCenterX, int roiCenterY, int roiWidth, int roiHeight, double roiAngle, int imageWidth, int imageHeight);

	bool computeImtDiameter(const ImageBGRA& img, double timestamp);

	vector<PointI> getContourLI() const;
	vector<PointI> getContourMA() const;
	float getDiameter(void) const;
	float getImt(void) const;

};

#endif // CARALGO_H
