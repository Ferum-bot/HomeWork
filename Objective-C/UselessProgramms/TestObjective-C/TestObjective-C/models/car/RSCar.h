//
//  RSCar.h
//  TestObjective-C
//
//  Created by Матвей Попов on 17.05.2021.
//

#import "RsVehicle.h"
#import "RSEnterable.h"

NS_ASSUME_NONNULL_BEGIN

@interface RSCar : RsVehicle <RSEnterable>

- (void)move;

@end

NS_ASSUME_NONNULL_END
